# AFK

AFK is a lightweight and highly configurable tool designed to keep track of players who are away from their keyboards in Minecraft. With its customizable features, server administrators can easily manage AFK status notifications, time thresholds, player kick options, and leverage the power of PlaceholderAPI.

## Key Features

- Lightweight: AFK is designed to be resource-friendly and will not cause any significant performance impact on your Minecraft server.
- Configurability: Customize the plugin to suit your server's needs. You can configure triggers that determine when a player is considered AFK and what actions to take.
- AFK Triggers: Define various triggers that indicate a player is not AFK, such as moving, sending messages, or interacting with the environment.
- Custom Messages: Tailor the messages displayed to players when they become AFK or return from being AFK with placeholders provided by PlaceholderAPI.
- Time Thresholds: Set the duration of inactivity before a player is considered AFK. Adjust the thresholds based on your server's gameplay style and preferences.
- Kick Option: Maintain an active player base by configuring the plugin whether to kick players when they become AFK or not.
- AFK Command: Use the `/afk` command to manually set your status as AFK. This is useful when you need to step away from your keyboard but want others to know you're away instantly.

## Placeholders

AFK supports PlaceholderAPI, allowing you to use placeholders in your messages and customize the display of AFK-related information. Here are some of the available placeholders:

- `%afk%`: If used in context of a player, returns whether said player is AFK. 
- `%afk_{player}%`: Returns whether the specified player is AFK (brackets are necessary).
- `%afk_list%`: Provides a list of players who are currently AFK.
- `%afk_prefixed%`: Displays how the player is represented in the tab when they are AFK (equivalent to `%afk_suffixed%`).

## Usage

1. Install AFK on your Minecraft server.
2. Configure the plugin settings based on your server's requirements, such as the AFK triggers, messages (utilizing PlaceholderAPI), time thresholds, and kick option.
3. Start the server and let the plugin automatically detect and manage AFK players.
4. Players will receive notifications when they become AFK and return from being AFK.
5. Utilize the tab menu to easily identify and monitor the AFK status of players on your server.
6. Use the `/afk` command to manually set your status as AFK when you need to step away.

AFK is a valuable addition to any Minecraft server, enhancing player engagement and promoting a more active community. Download and install it now to create a vibrant gaming environment!

Remember to configure your PlaceholderAPI placeholders to fully utilize the AFK plugin's capabilities.

**Please note**: PlaceholderAPI must be installed separately for the placeholders to function correctly.


## Development Status
AF is currently in early development. While the plugin has been tested and is functional, it is not yet feature-complete and may undergo significant changes in the future.
If you are interested in seeing further development of AFK, please feel free to reach out to me, as I will not update it unless someone is actually using it. Your feedback and suggestions are always welcome, and will help me to improve the plugin over time.
