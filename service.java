import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    
    public Ticket updateTicket(Long id, Ticket ticket) {
        if (ticketRepository.existsById(id)) {
            ticket.setId(id);
            return ticketRepository.save(ticket);
        } else {
            throw new IllegalArgumentException("Ticket not found with id: " + id);
        }
    }
    
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
    
    // Method for searching tickets by title and description
    public List<Ticket> searchTickets(String keyword) {
        return ticketRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }
}