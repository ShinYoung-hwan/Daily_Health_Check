
public class Cough extends Symptoms {

	public Cough() {
		super("Cough", 7, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getExplainationOfSymptom() {
		// TODO Auto-generated method stub

		return String.format("%s[%s]\n - %s\n", "Definition", "Cambridge Dictionary",
				"to force air out of your lungs through your throat with a short, loud sound.\n");
	}

}
