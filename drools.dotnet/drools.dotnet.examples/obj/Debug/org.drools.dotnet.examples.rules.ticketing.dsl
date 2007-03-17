#place your comments here - this is just a description for your own purposes.
[when]There is a customer ticket with status of "{status}"=customer : Customer( )   ticket : Ticket( Customer == customer, Status == "{status}" )
[when]There is a "{subscription}" customer with a ticket status of "{status}"=customer : Customer(Subscription == "{subscription}") ticket : Ticket( Customer == customer, Status == "{status}")
[then]Log "{Message}"=System.Console.Out.WriteLine("{Message} " + ticket.toString());
[then]Escalate the ticket=ticket.Status = "Escalate"; modify(ticket);
[then]Send escalation email=sendEscalationEmail( customer, ticket );

