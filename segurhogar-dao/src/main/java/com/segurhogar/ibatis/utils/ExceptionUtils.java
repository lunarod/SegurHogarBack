package com.segurhogar.ibatis.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.segurhogar.utils.exceptions.DaoExceptionRuntime;
import com.segurhogar.utils.exceptions.ErrorConstants;
import com.segurhogar.utils.exceptions.ModuleException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.Logger;
public class ExceptionUtils {

    public static ModuleException transformToHighLevelException(Throwable e){
        ModuleException me;

        if (e.getCause()!=null){

            Throwable ex = e.getCause();
            if (ex instanceof PersistenceException)
                me = new ModuleException("[MYBATIS] "+ ex.getMessage());
            else
                me = transformToHighLevelException(e.getCause());

        }else{
            if (e instanceof SQLException){
                String msg = e.getMessage();
                me = new ModuleException("[BBDD] "+msg);
                String errorCode = "";
                if (msg.contains("ORA-00001")){
                    errorCode = "ORA-00001";
                    msg = "Ya existe un campo existente con el mismo identificador. No se puede guardar.";
                }

                me.setServerErrorCode(errorCode);
                me.setMensaje(msg);
            }else if (e instanceof InvocationTargetException){
                me = transformToHighLevelException(((InvocationTargetException)e).getTargetException());

            }else if (e instanceof ModuleException){
                me = (ModuleException)e;
            }else{
                String line=null;
                if(e instanceof NullPointerException || e instanceof ClassCastException || e instanceof NumberFormatException ){
                    StackTraceElement[] elements =  e.getStackTrace();
                    line = " en "+ ((elements!=null && elements.length>0 && elements[0]!=null)?elements[0].getMethodName() + " " + elements[0].getLineNumber():0);
                }
                me = new ModuleException(e.getMessage() + (line!=null?line:""));
            }
        }
        return me;
    }

    public static ModuleException transformToHighLevelException(Throwable e, ErrorConstants.IError clientErrorCode){
        ModuleException me = transformToHighLevelException(e);
        if (me.getClientErrorCode()==null && me.getListClientErrorCodes().size()==0)
            me.setClientErrorCode(clientErrorCode);
        return me;
    }

    /**
     * Devuelve el mensaje de la excepción raíz y/o primera línea del stacktrace
     */
    public static String extractExceptionMessage(Throwable e){

        if(e==null){
            return "No se puede obtener traza de una excepción null";
        }

        String message;

        Throwable aux = e.getCause();

        StringBuilder messageBuilder = new StringBuilder(e.getMessage() == null ? "" : e.getMessage());
        while(aux!=null && !e.equals(aux)){

            String auxMessage = aux.getMessage()==null?"":aux.getMessage();
            if(!auxMessage.isEmpty()){
                messageBuilder.append(messageBuilder.toString().isEmpty() ? "" : "\r\n*").append(auxMessage);
            }
            e = aux;
            aux= aux.getCause();
        }
        message = messageBuilder.toString();

        if(message.isEmpty() || (e instanceof RuntimeException) ){
            message = (message.isEmpty()?"":message + "\r\n*Stack\r\n") + extractPrintStackTrace(e) ;
        }

        return message;

    }

    public static String extractPrintStackTrace(Throwable e){

        if(e==null){
            return "No se puede obtener traza de una excepción null";
        }

        StringBuilder stack = new StringBuilder("No se ha podido recuperar stackTrace de " + e.getClass());

        try{
            StackTraceElement[] stackTrace = e.getStackTrace();
            if(stackTrace!=null){
                int i=25;
                stack = new StringBuilder(e.getClass() + "*:");
                for(StackTraceElement element:stackTrace){
                    stack.append("\r\n\t").append(element.toString());
                    if(i--==0){
                        break;
                    }
                }
            }
        }
        catch(Exception ex){
            StackTraceElement[] stackTrace = e.getStackTrace();

            stack.append(" Stack: ").append(stackTrace == null ? "null" : stackTrace.length);
            if(stackTrace!=null && stackTrace.length>0 && stackTrace[0]!=null){
                stack = new StringBuilder(stackTrace[0].toString());
            }
        }

        return stack.toString();
    }

    public static void handleException (Throwable e, String methodName, Logger log, String info) {

        String moreInfo = (info!= null && !info.isEmpty()) ? info: "No additional Info";
        if(e instanceof DaoExceptionRuntime || e.getCause() instanceof DaoExceptionRuntime) {
            log.error("Error en la operacion/metodo " + methodName + " DaoExceptionRuntime. +Info: " + moreInfo);
        }else {
            log.error("Error en la operacion/metodo " + methodName + ". +Info: " + moreInfo, e);
        }
    }

}