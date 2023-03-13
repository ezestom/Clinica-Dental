package com.dh.clinica.service;

import com.dh.clinica.dao.IDao;
import com.dh.clinica.model.Odontologo;

import java.util.List;

public class OdontoloService {
  private IDao<Odontologo> odontologoIDao;
  
  public OdontoloService(IDao<Odontologo> odontologoIDao) {
    this.odontologoIDao = odontologoIDao;
  }
  
  public Odontologo guardar(Odontologo o){
    return odontologoIDao.guardar(o);
  }
  public Odontologo buscar(int id){
    return odontologoIDao.buscar(id);
  }
  public List<Odontologo> buscarTodos(){
    return odontologoIDao.buscarTodos();
  }
  public void eliminar(int id){
    odontologoIDao.eliminar(id);
  }
  
  public void actualizar(Odontologo o){
    odontologoIDao.actualizar(o);
  }
}
