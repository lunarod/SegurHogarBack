package com.segurhogar.ibatis.utils;

public class BBDD {

	public enum Condition{
		
		IS_NULL("IS NULL"),
		IS_NOT_NULL("IS NOT NULL"),
		EQUAL_TO("="),
		NOT_EQUAL_TO("<>"),
		GREATHER_EQUAL(">="),
		GREATHER(">"),
		LESS("<"),
		LESS_EQUAL("<="),
		NOT_IN("NOT IN"),
		IN("IN"),
		BETWEEN("BETWEEN"),
		NOT_BETWEEN("NOT BETWEEN"),
		LIKE("LIKE"),
		NOT_LIKE("NOT LIKE");
		
		public final String valor;
		
		private Condition(String valor){
			this.valor = valor;
		}
		
		public String getValor() {
			return valor;
		}
	}

	
	public interface IColumn{
		
		public String getValor();
		
	}
	
	public enum Alias {
		CLIENTE("CLIENTE");
		

		private final String valor;
		
		private Alias(String valor){
			this.valor = valor;
		}
		
		public String getValor() {
			return valor;
		}
	}

	public static class Column {
		public enum CLIENTE implements IColumn {
			ID_CLIENTE("ID_CLIENTE"),
			NOMBRE("NOMBRE"),
			APELLIDO1("APELLIDO1"),
			APELLIDO2("APELLIDO2"),
			DOCUMENTO("DOCUMENTO"),		
			FE_NACIMIENTO("FE_NACIMIENTO"),
			MAIL("MAIL"),
			TELEFONO("TELEFONO");
			
			private final String valor;
			
			CLIENTE(String valor){
				this.valor = valor;
			}

			@Override
			public String getValor() {
				return valor;
			}
		}
	}
}
