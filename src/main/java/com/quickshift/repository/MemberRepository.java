package com.quickshift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quickshift.entity.Member;
import com.quickshift.entity.Store;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	List<Member> findByStore(Store store);
<<<<<<< HEAD
=======
	Member findByStoreAndName(Store store, String name);
>>>>>>> e2089f548ec708cec6e496c0fa3c3e1deda457f7
	void deleteAllByStore(Store store);
	
	@Modifying
	@Query("UPDATE Member m SET m.name = :name WHERE m.id = :id")
	void updateName(@Param("id") Long id, @Param("name") String name);
}