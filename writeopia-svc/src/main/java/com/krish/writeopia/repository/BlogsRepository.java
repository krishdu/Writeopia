package com.krish.writeopia.repository;

import com.krish.writeopia.models.Blogs;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogsRepository extends CrudRepository<Blogs, Long> {
    List<Blogs> findAllByMyUsers_Id(Long id);

    List<Blogs> findAllByMyUsers_UserName(String userName);

    Blogs findBlogsById(Long id);
}