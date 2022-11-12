package web.culture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.culture.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
}
