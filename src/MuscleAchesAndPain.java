
public class MuscleAchesAndPain extends Symptoms {

	public MuscleAchesAndPain() {
		super("MuscleAchesAndPain", 2, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getExplainationOfSymptom() {
		// TODO Auto-generated method stub

		return String.format("%s[%s]\n - %s\n", "Definition", "Oxford Languages",
				"Pain in the muscles.\n");
	}
}
