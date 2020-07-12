package edu.metrostate.ics372.Group5;
public interface TicketVisitor {

    public void visit(Ticket ticket);

    public void visit(GeneralTicket generalTicket);

    public void visit(AdvanceTicket advanceTicket);

    public void visit(StudentTicket studentTicket);

}
