This update brings a lot of internal changes from the rework into the release version of Ad Astra.
It is initially marked as an alpha as there are a lot of internal changes, so it may break addons and
may be unstable.

## Changes
- Fixed lava turning into ice in space
- Fixed Ad Astra fluids in water tag
- Fixed rocket sound being stereo and not mono. This allows the audio to be directional.
- Machines no longer light up when active. This is to improve lighting performance.
- Large performance improvement to all machines
- Removed extinguished torches and lanterns. Torches and lanterns will simply break in space.
- Oxygen sensor block can now be inverted by shift-right clicking
- Oxygen, gravity and temperature are now saved in the world nbt. This should fix any issues where you don't have
  oxygen when you first open a world because it hasn't been calculated yet.
- Added public APIs for planets, oxygen, gravity, and temperature. Please use those instead of the internal ones
  when creating addons.
- Improved many tooltips. They now have more info and colored elements.
- Machines now display energy/fluid in/out per tick.
- Energy and fluid numbers now have prefixes (K, M, G). Shifting will show the full number.
- Fluid tanks can now be cleared by shift-right clicking
- Updated the plate and rod textures
- Added gas tanks. these replace oxygen tanks and can hold any fluid. Using them will distribute the
  stored fluid into any container in your inventory, with armor being the first priority.
- Added the zip gun. When filled with oxygen, it propels you forward. Holding two at the same time will propel you
  further.
- Separated the config into a common config and client config.
- Added redstone control to machines. These have 4 options: `ALWAYS_ON`, `ON_WHEN_POWERED`,
  `ON_WHEN_NOT_POWERED`, and `NEVER_ON`.
- Added a slot in energy machines for powering them with items.
- Added side configuration to all machines. This allows you to control what each element
of the machine does to all sides. For example, you can configure a machine to pull items on
one side, extract energy from another side, and push fluid to another side.
- Added experimental orbit physics. This enables zero gravity movement while in orbit.
- Added an experimental "Air Vortex." This spawns when you break or otherwise open a space in
a sealed oxygenated structure in orbit or any planet with no oxygen. It violently sucks
everything out of the structure and launches it, undergoing rapid depressurization.
Now you can eject the impostor :)
- All blocks and items are now properly datagened. this should fix any sort of weird discrepancies
with some models as they are no longer manually created.
- Removed meteors. They'll be re-added in the future but for now they don't need to exist and are more annoying, if anything.
- Added a button in the oxygen distributor to highlight all blocks that it's distributing to.
This replaces the original oxygen bubble particle indicator. This is also now entirely
client-side and saved to the client config.
- Rewrote JEI and REI integration, with improved screens and tooltips.
- Flags now render the second skin layer.
- Set max flag URL length from 32 to 64 characters.
- Changed package name from `earth.terrarium.ad_astra` to `earth.terrarium.adastra`. The mod id has not changed.
- Sliding doors can now be locked by right-clicking with a wrench. Locked sliding doors
can only be opened with redstone, similar to iron doors.
- Each rocket now has its own fuel tag. This allows packmakers to require different fuels for different rockets.
- Coal generator will no longer continue consuming fuel if it's full on energy.
- Updated the rover driving mechanics to be more fluid and easier to control.
- The rover can now run over mobs
- Fluid ducts now have the same internal mechanics as pipes so they can now be used exactly like them.
- Added the radio. This is a real radio that'll play actual select radio stations. It's available as a
screen in the rover. The default keybind is `R`.
- The Rocket sound is now looped. This allows it to play for as long as the rocket is flying.
- Fixed rocket sound not playing when leaving the world and rejoining while in a flying rocket.
- Fixed rocket particles not visible above world height with Embeddium/Sodium installed.
- Fixed rocket particles lagging behind the rocket on servers.
- Fixed DFU running on all Ad Astra structures due to them being saved on an older version
- Removed the hammer. Use the compressor to obtain plates instead.
- Each orbit can now have its owned recipe defined via datapack, instead of all of them using the same recipe.
- Reduced oil well spawn rates.
- Launchpads can now launch rockets when powered with redstone.
- Lander boosters now have a sound when used.
- The compressor can now compress storage blocks into 9 plates at once.
- The owner and location of space stations are now saved in the world nbt. This allows
you to land at your space station from anywhere and also prevents accidentally spawning
a new space station on an existing one.
- Updated the planets screen with a cleaner UI. You should be able to navigate the menu easier. And it 
should now be clear when you want to land on the planet, land on the space station or build a new space station.
- You can no longer teleport to orbit directly in the planets menu.
You must build a space station and land on that space station first.

## Breaking Changes
- storage blocks like desh blocks, steel blocks, etc. have had an ID change. Turn them into ingots before
updating so that you don't lose them.
- Removed Glacian signs, and aeronos and strophar chests.
- The JSON format for Ad Astra planets has changed a bit. You'll need
to update your existing planet file for it to work. Check the GitHub wiki (when it's updated).
- Some recipe IDs have been updated. It won't break anything in-game, but 
if you're using these recipes in modpacks, you'll need to change them. This includes 
  - "ad_astra:space_station_recipe" -> "ad_astra:space_station"
  - "ad_astra:cryo_fuel_conversion" -> "ad_astra:cryo_freezing"
  - "ad_astra:fuel_conversion" -> "ad_astra:refining"
  - "ad_astra:oxygen_conversion" -> "ad_astra:oxygen_loading"

## TODO: Not sure if this'll be in this release

- Added Cadmus compat to space stations. It'll automatically claim the chunks around your space station
and prevent players from spawning a new space station at any claimed chunks. 
- Added Argonauts compat to space stations. Players on the same guild will be
able to share space stations.
- Copper no longer oxidizes in environments with no oxygen.
- New Glacian Ram model.
- Added the Ti-69. It's a device that displays the local oxygen, temperature, and gravity.
- The warning to hold space when landing is now displayed boldly on screen instead of a chat message.
Hopefully this will prevent people from missing it and crashing.
- Fixed players dismounting landers with some mods like Transit Railway.
- Added a keybind to enable and disable the Jetsuit.
- Sliding doors can now be redstone powered from any of its blocks, instead of just the bottom middle one.
- Updated the oxygen distributor model. It now spins and can be placed on any block face.
- Added the gravity normalizer. This allows you to control the gravity of a local area, similar
to the oxygen distributor. It's especially important in orbit due to the zero gravity environment.