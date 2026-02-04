package samebutdifferent.ecologics.config;

public class ConfigCommon 
{
	private static float coconutCrabSpawnChance = 0.4F;
	private static boolean replaceAzaleaTree = true;
	private static boolean foxesAttackSquirrels = true;
	
	public static float coconutCrabSpawnChance() {
		return coconutCrabSpawnChance;
	}
	
	public static boolean replaceAzaleaTree() {
		return replaceAzaleaTree;
	}
	
	public static boolean foxesAttackSquirrels() {
		return foxesAttackSquirrels;
	}
}
