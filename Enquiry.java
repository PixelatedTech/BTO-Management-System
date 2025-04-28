package Assignment;

public class Enquiry {
	
	private String text;
	private String reply;

    public Enquiry(String text) {
        this.text = text;
        this.reply = "";
    }
    

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getReply() { return reply; }
    public void setReply(String reply) { this.reply = reply; }
    
    @Override
    public String toString() {
        return "Enquiry: " + text + (reply.isEmpty() ? "" : " | Reply: " + reply);
    }

}
