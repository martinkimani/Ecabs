/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecabs.challenge.web;

import com.ecabs.challenge.model.Response;
import com.ecabs.challenge.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author martin
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AUthController {

    @PostMapping("/generate-token")
    public ResponseEntity login(@RequestBody String loginUser) {

        return new ResponseEntity(new Response(0, AppConstants.generateToken()), HttpStatus.OK);
    }
}
