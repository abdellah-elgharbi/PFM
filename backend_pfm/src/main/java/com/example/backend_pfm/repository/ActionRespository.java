package com.example.backend_pfm.repository;

import com.example.backend_pfm.beans.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActionRespository extends JpaRepository<Action, Integer> {

    List<Action> findByDonatorId(Long donatorId);
    List<Action> findByReceiverId(Long receiverId);

}
