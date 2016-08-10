/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogue.springboot.service;

import blogue.springboot.domain.Blogue;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author DyoRizqal
 */
public interface BlogueDao extends PagingAndSortingRepository<Blogue, String>{
    
}
