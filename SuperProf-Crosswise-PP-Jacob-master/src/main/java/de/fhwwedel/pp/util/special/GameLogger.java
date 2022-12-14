/*
 * Copyright Notice for Crosswise-PP
 * Copyright (c) at Crosswise-Jacob 2022
 * File created on 7/28/22, 4:17 PM by Carina The Latest changes made by Carina on 7/28/22, 4:17 PM All contents of "Logger" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Crosswise-Jacob. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Crosswise-Jacob.
 */

package de.fhwwedel.pp.util.special;

import de.fhwwedel.pp.CrossWise;
import de.fhwwedel.pp.game.Game;
import de.fhwwedel.pp.util.game.Player;
import de.fhwwedel.pp.util.game.Position;
import de.fhwwedel.pp.util.game.Token;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameLogger {

    private GameLogger() {
    }

    private static final ArrayList<String> logMessages = new ArrayList<>();

    public static void log(String logMessage) {
        //get the current time and date
        if (CrossWise.DEBUG)
            System.out.println(logMessage);
        logMessages.add(new java.util.Date() + ": " + logMessage);
    }

    public static void logMove(Player player, Token placed, Position to) {
        String builder = "Player: \"" + player.getName() + "\" and ID: \"" + player.getPlayerID() + "\" " + Action.PLACE.getText() + ": " + placed.getTokenType().getValue() + " on: " + to.toString() + " new Hand: " + player.handRepresentation() +
                "\n" +
                Game.getGame().getPlayingField().toString();
        log(builder);
    }

    public static void logMoveMove(Player player, Position from, Position to) {
        String builder = "Player: \"" + player.getName() + "\" and ID: \"" + player.getPlayerID() + "\" " + Action.MOVED.getText() + " from: " + from.toStringWithToken() + " to: " + to.toString() + " new Hand: " + player.handRepresentation() +
                "\n" +
                Game.getGame().getPlayingField().toString();
        log(builder);
    }

    public static void logMoveRemove(Player player, Position from) {
        String builder = "Player: \"" + player.getName() + "\" and ID: \"" + player.getPlayerID() + "\" " + Action.REMOVE.getText() + " Token: " + from.getToken().getTokenType().getValue() + " from: " + from + " new Hand: " + player.handRepresentation() +
                "\n" +
                Game.getGame().getPlayingField().toString();
        log(builder);
    }

    public static void logMoveSwapper(Player player, Position start, Position to) {
        String builder = "Player: \"" + player.getName() + "\" and ID: \"" + player.getPlayerID() + "\" " + Action.SWAPPED.getText() + ": " + start.toStringWithToken() + " with " + to.toStringWithToken() + " new Hand: " + player.handRepresentation() +
                "\n" +
                Game.getGame().getPlayingField().toString();
        log(builder);
    }

    public static void logMoveReplacer(Player player, Position start, Token with) {
        String builder = "Player: \"" + player.getName() + "\" and ID: \"" + player.getPlayerID() + "\" " + Action.REPLACED.getText() + ": " + start.toStringWithToken() + " with vaule: " + with.getTokenType().getValue() + " new Hand: " + player.handRepresentation() +
                "\n" +
                Game.getGame().getPlayingField().toString();
        log(builder);
    }

    public static void saveLogToFile(String fileName) {
        File file = new File(fileName + ".txt");
        try (var writer = new FileWriter(file)) {
            for (var logMessage : logMessages) {
                writer.write(logMessage + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logDraw(Player player, Token token) {
        String builder = "Player \"" + player.getName() + "\" and ID: \"" + player.getPlayerID() + "\" draws: \"" + token.getTokenType().getValue() + "\" new Hand: " + player.handRepresentation() + "\n";
        log(builder);
    }
}
