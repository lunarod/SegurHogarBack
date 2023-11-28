package com.segurhogar.ibatis.mapper;

import com.segurhogar.models.ClienteModel;
import com.segurhogar.ibatis.example.BaseExample;

public interface ClienteMapper {
	
	ClienteModel inserta(ClienteModel model);
	ClienteModel getDetailClient (BaseExample example);
}
