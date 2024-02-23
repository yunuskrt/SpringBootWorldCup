package com.dreamgames.backendengineeringcasestudy.model;

public class PlayerInfo {
    private Long id;
    private String username;
    private String country;
    private int coin;
    private int level;

    public PlayerInfo() {
    }

    public PlayerInfo(Player player, PlayerProgress playerProgress) {
        this.id = player.getId();
        this.username = player.getUsername();
        this.country = player.getCountry();
        this.coin = playerProgress.getCoin();
        this.level = playerProgress.getLevel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
