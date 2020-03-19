package application;


public class NotebookModel {
	String nbName;
	public NotebookModel(String nbName) {
		this.nbName = nbName;
	}
	
	public String getNbName() {
		return nbName;
	}
	
	public void setNbName(String s) {
		this.nbName = s;
	}
}
