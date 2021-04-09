# MNWL :: Moris & Never White List plugin

## Configuration

All plugin configurations are contained in the *config.yml* file.
</br>
The settings responsible for chat messages are located in the *messages* block.

```yaml
messages:
   kickMessage: 'You are not in white list!'
   permDenied: 'Permission denied'
   plrExist: 'Player already exist'
   plrNotExist: 'Player not exist'
   plrAdded: 'Player {name} was added to white list'
   plrRemoved: 'Player {name} was removed from white list'
```

| value | message |
| -------: | :------- |
| kickMessage | Message displayed when a player is removed |
| permDenied | Permission denied |
| plrExist | The player exists |
| plrNotExist | The player doesn't exists |
| plrAdded | Message displayed when a player is added to the whitelist |
| plrRemoved | The message is displayed when a player is removed from the whitelist |

## Commands
To view the list of players, use the command *wlist*:
```
/wlist
```

To add a player to the whitelist, use the command *wladd*:
```
/wladd [nickname]
```

To remove a player from the whitelis, use the command *wlremove*:
```
/wlremove [nickname]
```