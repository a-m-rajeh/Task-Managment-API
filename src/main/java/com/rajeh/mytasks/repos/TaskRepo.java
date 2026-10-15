
 package com.rajeh.mytasks.repos;


 import com.rajeh.mytasks.entities.Task;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;


@Repository
 public interface TaskRepo  extends JpaRepository<Task, Integer> {
 // save
 // update
 // delete
 //count
}