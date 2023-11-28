package com.segurhogar.negocio;

import com.segurhogar.ibatis.example.BaseExample;
import com.segurhogar.ibatis.example.BaseExample.Criteria;
import com.segurhogar.ibatis.mapper.ClienteMapper;
import com.segurhogar.di.JtaManager;
import com.segurhogar.ibatis.utils.BBDD.Column;
import com.segurhogar.ibatis.utils.BBDD.Condition;
import com.segurhogar.ibatis.utils.ExceptionUtils;
import com.segurhogar.utils.exceptions.ErrorConstants;
import com.segurhogar.utils.exceptions.ModuleException;
import com.segurhogar.utils.exceptions.DaoExceptionRuntime;
import com.segurhogar.models.ClienteModel;
import com.segurhogar.remote.ClienteBeanRemote;
import org.apache.log4j.Logger;
import org.mybatis.cdi.Mapper;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;


@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteBean implements ClienteBeanRemote {

    @Inject  
    @Mapper  
    @JtaManager private ClienteMapper clienteMapper;
    @Inject private transient Logger logger;

    @Override
    public ClienteModel newClient(ClienteModel cliente) {
        try{
            logger.info("[newClient] - inicio de la insercion de un cliente.");
            return clienteMapper.inserta(cliente);
            
        }catch(Exception e){
            ModuleException me = ExceptionUtils.transformToHighLevelException(e,
                    ErrorConstants.EError.ERROR_0000);
            logger.error("[newClient] - Excepcion al insertar un cliente. \n", me);
            throw me;
        }

    }

	@Override
	public ClienteModel getDetailClient(ClienteModel c) {
        BaseExample example = new BaseExample();
        Criteria criteria = example.createCriteria();
        
        
        criteria.addCondition(Column.CLIENTE.ID_CLIENTE, Condition.EQUAL_TO, c.getID_CLIENTE()); 
        
        ClienteModel cliente = clienteMapper.getDetailClient(example);
        
        return cliente;
	}
}
