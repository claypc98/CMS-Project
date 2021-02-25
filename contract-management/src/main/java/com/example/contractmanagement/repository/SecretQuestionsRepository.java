/**
 * 
 */
package com.example.contractmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contractmanagement.model.SecretQuestions;

/**
 * @author Vennelakanti
 *
 */
public interface SecretQuestionsRepository extends JpaRepository<SecretQuestions, Integer> {

}
