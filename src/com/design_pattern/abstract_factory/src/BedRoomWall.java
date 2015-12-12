package com.design_pattern.abstract_factory.src;
/*
 * A concrete Wall for Living Room
 */
public class BedRoomWall extends Wall {
    private String wallName;
    public BedRoomWall() {
        wallName = "BedRoomWall";
    }
    public String getName() {
        return wallName;
    }
}
