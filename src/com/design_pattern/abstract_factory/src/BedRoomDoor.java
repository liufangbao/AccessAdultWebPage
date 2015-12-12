package com.design_pattern.abstract_factory.src;
/*
 * A concrete Door for Bed Room
 */
public class BedRoomDoor extends Door {
    private String doorName;
    public BedRoomDoor() {
        doorName = "BedRoomDoor";
    }
    public String getName() {
        return doorName;
    }
}
