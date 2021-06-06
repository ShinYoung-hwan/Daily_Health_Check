
public class Fever extends Symptoms {

	public Fever() {
		super("Fever", 9, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getExplainationOfSymptom() {
		// TODO Auto-generated method stub

		return String.format("%s[%s]\n - %s\n", "Definition", "Cambridge Dictionary",
				"a medical condition in which the body temperature is higher than usual and the heart beats very fast.\n");
	}

}
