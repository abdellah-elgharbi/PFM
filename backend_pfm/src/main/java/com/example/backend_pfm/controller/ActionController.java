package com.example.backend_pfm.controller;

import com.example.backend_pfm.repository.ActionRespository;
import com.example.backend_pfm.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend_pfm.beans.Action;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/action")
public class ActionController {
    @Autowired
    ActionRespository actionRespository;
    @Autowired
    UserRespository userRespository;
    @PostMapping
    public ResponseEntity<com.example.backend_pfm.beans.Action> createAction(@RequestBody Action action) {
        Action savedAction = actionRespository.save(action);
        return ResponseEntity.ok((com.example.backend_pfm.beans.Action) savedAction); // Return the saved action
    }
    @GetMapping
    List<com.example.backend_pfm.beans.Action> getAllActions() {
        return  actionRespository.findAll();
    }
    @GetMapping("/{id}")
    List<com.example.backend_pfm.beans.Action> getActionById(@PathVariable int id) {
        Optional<com.example.backend_pfm.beans.Action> action = actionRespository.findById(id);
        return (List<com.example.backend_pfm.beans.Action>) action.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAction(@PathVariable int id) {
        Optional<com.example.backend_pfm.beans.Action> actionOpt = actionRespository.findById(id);
        if (!actionOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        actionRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/donator/{donatorId}")
    public List<Action> getActionsByDonator(@PathVariable Long donatorId) {
        return actionRespository.findByDonatorId(donatorId);
    }
    @GetMapping("/receiver/{receiverId}")
    public List<Action> getActionsByReceiver(@PathVariable Long receiverId) {
        return actionRespository.findByReceiverId(receiverId);
    }
}
