package samebutdifferent.ecologics.config;

public class ConfigCommon 
{
	private static double coconutCrabSpawnChance = 0.25F;
	private static double pricklyPearGrowthChance = 0.4F;
	private static double mapleSapDepletionChance = 0.2F;
	private static boolean camelChestsEnabled = true;
	private static boolean replaceAzaleaTree = true;
	private static boolean foxesAttackSquirrels = true;
	private static boolean useVanillaTabs = true;
	
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
	
	public static void setCamelChestsEnabled(boolean newValue) {
		camelChestsEnabled = newValue;
	}
	
	public static boolean getCamelChestsEnabled() {
		return camelChestsEnabled;
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
	
	public static void setMapleSapDepletionChance(double newValue) {
		mapleSapDepletionChance = newValue;
	}
	
	public static double getMapleSapleDepletionChance() {
		return mapleSapDepletionChance;
	}
	
	public static void setUseVanillaTabs(boolean newValue) {
		useVanillaTabs = newValue;
	}
	
	public static boolean getUseVanillaTabs() {
		return useVanillaTabs;
	}
}
