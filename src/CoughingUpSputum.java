
public class CoughingUpSputum extends Symptoms{

	public CoughingUpSputum() {
		super("CoughingUpSputum", 3, false);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getExplainationOfSymptom() {
		// TODO Auto-generated method stub

		return String.format("%s[%s]\n - %s\n", "Definition", "Oxford Languages",
				"to force air out of your lungs through your throat with a short, loud sound with phlegm.\n");
	}
}
