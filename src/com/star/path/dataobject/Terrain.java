package com.star.path.dataobject;

/**
 * Enum class to represent the type of Tile
 * 
 * @author Rashid.O
 *
 */
public enum Terrain {

    WATER('~',-1),
    LAND('.',1),
    FOREST('*',2),
    MOUNTAIN('^',3),
    SOURCE('@',1), 
    DESTINATION('X',1), 
    SHORT_PATH('#',0);

    /** character read from map file **/
    private final char key;

    /** wightage for the tile to  move **/
    private final Integer value;

    /** constructor**/
    Terrain(final char key, final Integer value) {
	this.key = key;
	this.value = value;
    }

    /** Method to get character of a Terrain **/
    public char getKey() {
	return key;
    }

    /** Method to get wieghtage of Terrain **/
    public Integer getValue() {
	return value;
    }

    /** Method to get a Terrain with a a particular character **/
    public static Terrain getTerrileByChar(final char ch){

	for (Terrain terrain : Terrain.values()) {

	    if(terrain.getKey() == ch){
		return terrain;
	    }
	}
	return null;
    }

}
