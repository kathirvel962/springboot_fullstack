package com.example.trainbookingsystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trainbookingsystem.entity.Ticket;
import com.example.trainbookingsystem.entity.Train;
import com.example.trainbookingsystem.entity.User;
import com.example.trainbookingsystem.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    
    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        
         
        Ticket l = null;
        if (updatedTicket.getUser() != null) {
            ticket.setUser(l.getUser());
        }
        if (updatedTicket.getTrain() != null) {
            ticket.setTrain(l.getTrain());
            ticket.setFinalPrice(calculateTicketPrice(updatedTicket.getTrain().getBasePrice(), l.getTrain().getDiscountPercentage()));
        }
        ticket.setBookingDate(LocalDateTime.now()); // Update booking date
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public double calculateTicketPrice(double basePrice, double discountPercentage) {
        return basePrice - (basePrice * discountPercentage / 100);
    }

    public Ticket updateTicket(long id, long ticketDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTicket'");
    }
}