package com.example.demo.repo;

import com.example.demo.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<ContactModel, Long> {


    ContactModel findByEmail(String email);

    ContactModel findByPhone(String phone);
    Optional<ContactModel> findByIdAndIsActiveTrue(Long id);

    @Query(value = """
                select a.name, a.secondname, a.work, a.email, a.phone, a.description
                from contact a
                where 
                date_trunc('day',a.created_at) between TO_TIMESTAMP(:startDate,'dd-MM-yyyy') and TO_TIMESTAMP(:endDate,'dd-MM-yyyy')                
                order by created_at asc
            """, nativeQuery = true)
    List<Map<String, Object>> searchByParamStartDateAndEndDate(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate);
}
