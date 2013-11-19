package ch.bli.mez.model.dao;

import java.util.List;

public interface Searchable {

  public List<?> findByKeywords(String url);

}
