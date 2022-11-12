package web.culture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.culture.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer>{
}
