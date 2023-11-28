package com.segurhogar.ibatis.example;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.segurhogar.ibatis.utils.BBDD.Alias;
import com.segurhogar.ibatis.utils.BBDD.Condition;
import com.segurhogar.ibatis.utils.BBDD.IColumn;
import com.segurhogar.models.BaseModel;



public class BaseExample extends BaseModel {

	private static final long serialVersionUID = -4508829463361145174L;

    protected boolean distinct;
    protected boolean paginar = false;
    protected boolean isExistsCondition = false;
    protected boolean isExistsConditionColectivo = false;
    protected boolean isExistsBusquedaColectivo = false;
    protected boolean isTxString = false;
    private int offset = 0;
    private int limit  = 500; 
    protected List<Criteria> oredCriteria;
    protected String orderByClause;    //Campos para la paginación
	private int tamanoPagina=50; //valor por defecto de la paginacion	
	private int NU_PAGINA=1; //pagina por defecto
	private int NU_FIN = offset+limit;
	private int NU_INICIO = offset;
	
	private String NO_TABLE = "";
	private String TX_SELECT_STRING = "";

	
	
	/**
	 * @param tamanoPagina el tamaño de la pagina
	 */
	public void setTamanoPagina(int tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
		this.NU_PAGINA = 1;
		this.NU_FIN = this.NU_PAGINA*tamanoPagina;
		this.NU_INICIO = this.NU_FIN - tamanoPagina +1;
	}

	/**
	 * @param nu_pagina the NU_PAGINA to set
	 */
	public void setNU_PAGINA(int nu_pagina) {
		this.NU_PAGINA = nu_pagina;
		this.NU_FIN = this.NU_PAGINA*tamanoPagina;
		this.NU_INICIO = this.NU_FIN - tamanoPagina +1;
	}

	public int getTamanoPagina() {
		return tamanoPagina;
	}

	public int getNU_PAGINA() {
		return NU_PAGINA;
	}

	public int getNU_FIN() {
		return NU_FIN;
	}

	public int getNU_INICIO() {
		return NU_INICIO;
	}

	public boolean isExistsCondition() {
		return isExistsCondition;
	}

	public void setExistsCondition(boolean isExistsCondition) {
		this.isExistsCondition = isExistsCondition;
	}
		
	public boolean isExistsConditionColectivo() {
		return isExistsConditionColectivo;
	}

	public void setExistsConditionColectivo(boolean isExistsConditionColectivo) {
		this.isExistsConditionColectivo = isExistsConditionColectivo;
	}
	
	public boolean isExistsBusquedaColectivo() {
		return isExistsBusquedaColectivo;
	}

	public void setExistsBusquedaColectivo(boolean isExistsBusquedaColectivo) {
		this.isExistsBusquedaColectivo = isExistsBusquedaColectivo;
	}

	public BaseExample() {
		super();
		oredCriteria = new ArrayList<Criteria>();
	}

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public boolean isPaginar() {
		return paginar;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
		this.NU_FIN = offset+limit;
		this.NU_INICIO = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		this.NU_FIN = this.offset+limit;
	}

	public String getNO_TABLE() {
		return NO_TABLE;
	}

	public void setNO_TABLE(String nO_TABLE) {
		NO_TABLE = nO_TABLE;
	}

	public String getTX_SELECT_STRING() {
		return TX_SELECT_STRING;
	}

	public void setTX_SELECT_STRING(String tX_SELECT_STRING) {
		TX_SELECT_STRING = tX_SELECT_STRING;
	}

	public void setPaginar(boolean paginar) {
		this.paginar = paginar;
	}
	
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }
	
    public int getConditionNumber(){
    	int result = 0;
    	for (Criteria criteria: oredCriteria){
    		if (criteria.getCriteria()!=null)
    			result += criteria.getCriteria().size();
    	}
    	return result;
    }
    
    protected abstract static class GeneratedCriteria implements Serializable{

		private static final long serialVersionUID = -2652901187962107687L;		
		protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String cualificador, String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(cualificador, condition));
        }        
        
        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String cualificador, String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(cualificador, condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterion(String cualificador, String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(cualificador, condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String cualificador, String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(cualificador, condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String cualificador, String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(cualificador, condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String cualificador, String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(cualificador, condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }
        
        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }
        
        /** Criterios de los campos Base **/
        public Criteria addCondition(String property, Condition condicion, Object value) {
        	addCriterion(property +" " + condicion.getValor(), value, property);
        	return (Criteria) this;
        }
        
        /** Criterios de los campos Base **/
        public Criteria addCondition(IColumn property0, Condition condicion, IColumn property1) {
        	addCriterion(property0.getValor() + " " + condicion.getValor() + " " + property1.getValor());
        	return (Criteria) this;
        }
        
        //IS NULL and IS NOT NULL
        public Criteria addCondition(IColumn property, Condition condicion ) {
        	addCriterion(property.getValor() +" " + condicion.getValor());
        	return (Criteria) this;
        }
        
      //IS NULL and IS NOT NULL con ALIAS
        public Criteria addCondition(Alias cualificador, IColumn property, Condition condicion) {
        	addCriterion(cualificador.getValor(), property.getValor() +" " + condicion.getValor());
        	return (Criteria) this;
        }
        
        public Criteria addCondition(IColumn property, Condition condicion,  Object value ) {
       		addCriterion(property.getValor() +" " + condicion.getValor(), value, property.getValor());
        	return (Criteria) this;
        }
        

        public Criteria addLikeCondition(Alias cualificador, IColumn property, Condition condicion,  String value ) {
        	String temp = value.toUpperCase();
        	if (value.indexOf("%")!=-1)
        		condicion = Condition.LIKE;
        	addCriterion(cualificador.getValor(),property.getValor() +" " + condicion.getValor(), temp, property.getValor().toUpperCase());
        	return (Criteria) this;
        }
        
        public Criteria addLikeToUpperCondition(Alias cualificador, IColumn property, Condition condicion,  String value ) {
        	String temp = value.toUpperCase();
        	if (value.indexOf("%")!=-1) {
        		condicion = Condition.LIKE;
        		addCriterion("UPPER(" + property.getValor() +") " + condicion.getValor(), temp, property.getValor().toUpperCase());
        	}
        	else addCriterion(cualificador.getValor(),property.getValor() +" " + condicion.getValor(), temp, property.getValor().toUpperCase());
        	return (Criteria) this;
        }
        
        public Criteria addLikeCondition(IColumn property, Condition condicion,  String value ) {
        	String temp = value.toUpperCase();
        	if (value.indexOf("%")!=-1)
        		condicion = Condition.LIKE;
        	addCriterion(property.getValor() +" " + condicion.getValor(), temp, property.getValor().toUpperCase());
        	return (Criteria) this;
        }
        
        public Criteria addLikeConditionNotUpper(IColumn property, Condition condicion,  String value ) {
        	if (value.indexOf("%")!=-1)	
        		condicion = Condition.LIKE;
        	addCriterion(property.getValor() +" " + condicion.getValor(), value, property.getValor());
        	return (Criteria) this;
        }
        
        public Criteria addDynamicCondition(String columnProperty, Condition condicion,  Object value ) {
        	addCriterion(columnProperty +" " + condicion.getValor(), value, columnProperty);
        	return (Criteria) this;
        }
        
        public Criteria addUpperCondition(IColumn property, Condition condicion,  String value ) {
        	return addUpperCondition(property, condicion,  value, true );
        }
        
        public Criteria addUpperCondition(IColumn property, Condition condicion,  String value, boolean arg ) {
        	String temp = value.toUpperCase();
        	if(condicion.equals(Condition.LIKE)){
        		temp = temp + "%";
        		if (arg)
        			temp = "%" + temp;
        	}
        	addCriterion("UPPER(" + property.getValor() +") " + condicion.getValor(), temp,  property.getValor().toUpperCase());
        	return (Criteria) this;
        }
        
        public Criteria addUpperCondition(Alias cualificador, IColumn property, Condition condicion,  String value, boolean arg ) {
        	String temp = value.toUpperCase();
        	if(condicion.equals(Condition.LIKE)){
        		temp = temp + "%";
        		if (arg)
        			temp = "%" + temp;
        	}
        	addCriterion("UPPER(" + cualificador.getValor() + "." + property.getValor() +") " + condicion.getValor(), temp, cualificador.getValor() + "." + property.getValor().toUpperCase());
        	return (Criteria) this;
        }
        
        public Criteria addUpperCondition(Alias cualificador, IColumn property, Condition condicion,  String value ) {
        	return addUpperCondition(cualificador, property, condicion,  value, true );
        }
        
        public Criteria addCondition(Alias cualificador, IColumn property, Condition condicion,  Object value ) {
        	if(condicion.getValor().equals(Condition.LIKE.getValor()) && value.toString().indexOf("%") < 0)
        		addCriterion(cualificador.getValor(), property.getValor() +" " + condicion.getValor(), value+"%", property.getValor());
        	else
        		addCriterion(cualificador.getValor(), property.getValor() +" " + condicion.getValor(), value, property.getValor());
        	return (Criteria) this;
        }

        public Criteria addCondition(IColumn property, Condition condicion, Date value ) {
            if(condicion.equals(Condition.LESS_EQUAL) && value != null) {
            	condicion = Condition.LESS;
            	LocalDateTime hasta = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            	value = Date.from(hasta.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant());
            }
        	addCriterionForJDBCDate(property.getValor() +" " + condicion.getValor(), value, property.getValor());
        	return (Criteria) this;
        }
        
        public Criteria addCondition(Alias cualificador, IColumn property, Condition condicion, Date value ) {
        	if(condicion.equals(Condition.LESS_EQUAL) && value != null) {
        		condicion = Condition.LESS;
        		LocalDateTime hasta = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            	value = Date.from(hasta.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant());
            }
        	addCriterionForJDBCDate(cualificador.getValor(), property.getValor() +" " + condicion.getValor(), value, property.getValor());
        	return (Criteria) this;
        }
        
        public Criteria addConditionDate(Alias cualificador, IColumn property, Condition condicion, Date value ) {
        	LocalDateTime dt = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        	value = Date.from(dt.withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant());
        	addCriterionForJDBCDate(cualificador.getValor(), property.getValor() +" " + condicion.getValor(), value, property.getValor());
        	return (Criteria) this;
        }
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
		public Criteria addCondition(IColumn property, Condition condicion, List values ) {
        	if (values !=null && values.size()>0 && values.get(0) instanceof Date)
    			addCriterionForJDBCDate(property.getValor() +" " + condicion.getValor(), values, property.getValor());
        	else
        		addCriterion(property.getValor() +" " + condicion.getValor(), values, property.getValor());
        	return (Criteria) this;
        }
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
		public Criteria addCondition(Alias cualificador, IColumn property, Condition condicion, List values ) {
        	if (values !=null && values.size()>0 && values.get(0) instanceof Date)
    			addCriterionForJDBCDate(cualificador.getValor(), property.getValor() +" " + condicion.getValor(), values, property.getValor());
        	else
        		addCriterion(cualificador.getValor(), property.getValor() +" " + condicion.getValor(), values, property.getValor());
        	return (Criteria) this;
        }
        
        public Criteria addCondition(IColumn property, Condition condicion, Object value1, Object value2 ) {
        	if (value1 instanceof Date ) {
        		
        		Date val2Aux = (Date) value2;
        		Date val1Aux = (Date) value1;
        		LocalDateTime hasta = new Date(val2Aux.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    			LocalDateTime desde = new Date(val1Aux.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        		if(condicion.equals(Condition.BETWEEN) && value2 != null) {
        			addCriterionForJDBCDate(property.getValor() +" " + Condition.GREATHER_EQUAL.getValor(),Date.from(desde.withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant()), 
        					property.getValor());
                	addCriterionForJDBCDate(property.getValor() +" " + Condition.LESS.getValor(), Date.from(hasta.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant()), 
                			property.getValor());
                }else{
                	addCriterionForJDBCDate(property.getValor() +" " + condicion.getValor(),Date.from(desde.withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant()), 
                			Date.from(hasta.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant()), property.getValor());
                }
        	} else
        		addCriterion(property.getValor() +" " + condicion.getValor(), value1, value2, property.getValor());
        	return (Criteria) this;
        }
        
        public Criteria addCondition(Alias cualificador, IColumn property, Condition condicion, Object value1, Object value2 ) {
        	if (value1 instanceof Date ) {
        		Date val2Aux = (Date) value2;
        		Date val1Aux = (Date) value1;
        		LocalDateTime hasta = new Date(val2Aux.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    			LocalDateTime desde = new Date(val1Aux.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        		if(condicion.equals(Condition.BETWEEN) && value2 != null) {

                	addCriterionForJDBCDate(cualificador.getValor(),property.getValor() +" " + Condition.GREATHER_EQUAL.getValor(),Date.from(desde.withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant())
                			, property.getValor());
                	addCriterionForJDBCDate(cualificador.getValor(),property.getValor() +" " + Condition.LESS.getValor(), Date.from(hasta.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant())
                			, property.getValor());
                }else{
                	addCriterionForJDBCDate(cualificador.getValor(), property.getValor() +" " + condicion.getValor(),
                			Date.from(desde.withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant()), 
                			Date.from(hasta.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toInstant()), property.getValor());
                }
        	} else
        		addCriterion(cualificador.getValor(), property.getValor() +" " + condicion.getValor(), value1, value2, property.getValor());
        	return (Criteria) this;
        }
        
        //nvl(alias.columna,valor) = valor
        public Criteria addConditionOrNullValue(Alias cualificador, IColumn property, Condition condicion,  Object value ) {
        	if(condicion.getValor().equals(Condition.LIKE.getValor()) && value.toString().indexOf("%") < 0)
        		addCriterion("NVL( "+cualificador.getValor()+"."+property.getValor() +", UPPER("+value+"))" + condicion.getValor(), value+"%", property.getValor().toUpperCase());
        	else
        		addCriterion("NVL( "+cualificador.getValor()+"."+property.getValor() +", UPPER("+value+"))" + condicion.getValor(), value, property.getValor());
        	return (Criteria) this;
        }
        
        public Criteria addOredCriteria(Criteria oredCriteria){
        	criteria.add(new Criterion(oredCriteria,true));
        	return (Criteria) this;
        }
        
        public Criteria addCriteriaMultiple(Criteria oredCriteria){
        	criteria.add(new Criterion(oredCriteria,false));
        	return (Criteria) this;
        }
        
        
        
       
    }

    
    public static class Criteria extends GeneratedCriteria implements Serializable{

		private static final long serialVersionUID = -7305805366409762399L;

		protected Criteria() {
            super();
        }
		
		public static Criteria generateCriteria(){
			return new Criteria();
		}
    }


    
    public static class Criterion implements Serializable {
	    
		private static final long serialVersionUID = 4646184771388392169L;
		
		protected String cualificador="";
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private boolean orValue;
        private Criteria oredCriteria;
        
        public String getCondition() {
            return (cualificador!=null && !cualificador.trim().equals(""))? cualificador+"."+condition : condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }
        
        public boolean isOrValue() {
			return orValue;
		}
               
        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }
        
        protected Criterion(String cualificador,String condition) {
            super();
            this.cualificador = cualificador;
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(Criteria oredCriteria,boolean orValue) {
        	super();
        	if (oredCriteria!=null && oredCriteria.isValid()){
        		if (orValue)
        			this.orValue = true;
        		else this.orValue = false;
        		this.oredCriteria = oredCriteria;
        	}
        }
        
        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }
        
        protected Criterion(String cualificador,String condition, Object value) {
            super();
            this.cualificador = cualificador;
            this.condition = condition;
            if (value instanceof String)
            	this.value = value;
            else
            	this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            if (value instanceof String)
            	this.value = value;
            else
            	this.value = value;
            if (secondValue instanceof String)
            	this.secondValue = secondValue;
            else
            	this.secondValue = secondValue;
            this.betweenValue = true;
        }
        
        protected Criterion(String cualificador,String condition, Object value, Object secondValue) {
            super();
            this.cualificador = cualificador;
            this.condition = condition;
            if (value instanceof String)
            	this.value = value;
            else
            	this.value = value;
            
            if (secondValue instanceof String)
            	this.secondValue = secondValue;
            else
            	this.secondValue = secondValue;
            this.betweenValue = true;
        }
        
        public void setCualificador(String cualificador) {
			this.cualificador = cualificador;
		}

		public String getCualificador() {
			return cualificador;
		}
		
		public Criteria getOredCriteria() {
			return oredCriteria;
		}
    }



	public boolean isTxString() {
		return isTxString;
	}

	public void setTxString(boolean isTxString) {
		this.isTxString = isTxString;
	}
}