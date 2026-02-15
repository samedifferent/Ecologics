package samebutdifferent.ecologics.config;

public class ConfigCommon 
{
	private static double coconutCrabSpawnChance = 0.25F;
	private static double pricklyPearGrowthChance = 0.4F;
	private static boolean replaceAzaleaTree = true;
	private static boolean foxesAttackSquirrels = true;
	
	public static void setCoconutCrabSpawnChance(double newValue) {
		coconutCrabSpawnChance = newValue;
	}
	
	public static double getCoconutCrabSpawnChance() {
		return coconutCrabSpawnChance;
	}
	
	public static void setPricklyPearGrowthChance(double newValue) {
		pricklyPearGrowthChance = newValue;
	}
	
	public static double getPricklyPearGrowthChance() {
		return pricklyPearGrowthChance;
	}
	
	public static void setReplaceAzaleaTree(boolean newValue) {
		replaceAzaleaTree = newValue;
	}
	
	public static boolean getReplaceAzaleaTree() {
		return replaceAzaleaTree;
	}

	public static void setFoxesAttackSquirrels(boolean newValue) {
		foxesAttackSquirrels = newValue;
	}
	
	public static boolean getFoxesAttackSquirrels() {
		return foxesAttackSquirrels;
	}
}
