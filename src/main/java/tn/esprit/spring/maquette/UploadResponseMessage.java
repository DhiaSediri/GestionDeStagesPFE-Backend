package tn.esprit.spring.maquette;

public class UploadResponseMessage {

	private final String responseMessage;

    public UploadResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
    
}
