# Godzillagotchi 3.5

Unofficial Android 8+ virtual-pet game by Nova, designed around a 720 × 720 screen.

## Install
Open Godzillagotchi-3.5.apk on the Android device and install over the existing app. Do not uninstall first. The package and signing key are unchanged; accumulated XP and care stats are retained. Growth stages are recalculated using the new, slower thresholds.

## New in 3.5

Poop taps play the supplied slime splatter; CLEAN still plays bath bubbles. The launcher icon is the approved grumpy vinyl Godzilla. PLAY > CITY STOMP now lets you choose one target: the original tower, Japanese shiro, Eiffel Tower, Leaning Tower of Pisa, Trump Tower, Burj Khalifa, or Taj Mahal. One building ends the outing after its collapse. All targets still take multiple hits, with animated attacks, moving military threats, and early exit. All four themes have matching landmark sprites. Existing active city saves migrate to a single remaining tower; pet progress is retained.

## New in 3.4

FEED > KAIJU RUSH restores 33 energy points (33% of the full bar), capped at 100. A teal can lifts and tilts to drink, with a sip/gulp sound, in all four themes. Chewing now lasts 1.6 seconds instead of 0.68 seconds; Kong's special roar lasts 2.0 seconds instead of 5.68 seconds. Saved pets and signing identity are retained.

## New in 3.3

Opponent roars only accompany special attacks. Kong throws a large spinning barrel that breaks on impact. Each destroyed military vehicle independently has a 5% chance to trigger the Wilhelm scream. Feeding uses recorded chewing. Tap individual messes to clean them up. Opponent poses use a consistent height per monster and resolve theme artwork once, eliminating duplicate fitting and attack-only size overrides.

## New in 3.2: care sounds and opponent roars
CLEAN now plays bubbling water for the bath. FEED plays a short munching sequence when the fish reaches Godzilla's mouth. All six opponents have their own classic roar recording, played only at special-attack wind-up (updated in 3.3). Sounds work in all four themes and use your device's media volume. The existing Godzilla roar and impact sounds remain.

Pending chewing and active effects are cancelled when you leave the action or background the app. Roars are stopped when leaving fights; resuming does not replay old sound cues. No pet reset or theme change occurs on installation. See [sound credits, processing and verification](art/SOUNDS.md). Android-device playback testing remains outstanding.

## New in 3.1: four saved themes
Open **CARE > THEME** and choose **Classic**, **Puppet Theater**, **Vinyl Toy**, or **LCD**. The choice takes effect immediately and survives reopening and starting a new pet. Upgrades default to the existing Puppet Theater look. Themes are cosmetic: pet progress, care, cooldowns, combat and city encounters are unchanged.

- Classic: original pixel Godzilla, sleep/roar/care poses, attacks, opponents, three fish and day/night skyline, with geometric care effects and city vehicles.
- Puppet Theater: existing colored-pencil paper sprites, tears, falling paper, helicopter strings and tank sticks.
- Vinyl Toy: the approved compact blind-box Godzilla, matching opponents and attacks, molded toy accessories, buildings and day/night playset.
- LCD: fixed-segment monochrome sprites and care items, gray-green display, matching HUD/buttons and simple LCD buildings/vehicles. Day/night stays visible. Active targets remain distinguishable over the skyline.

All main controls retain the same 720-square hit areas. Only current-theme Android bitmaps are loaded. ThemeTest exercises selection persistence, new-pet retention, legacy defaults, asset references and all player attacks/collapses in every theme. ThemePreview renders 52 care/battle/city combinations using the production renderer. Artwork prompts: art/THEME_ARTWORK.md. Android device testing remains outstanding.

## New in 3.0: colored-pencil paper theater
Godzilla, all six opponents and their attack poses, three fish foods, intact/cracked eggs, soap bubbles, messes, hearts, moon, book and medicine now use matching paper cutout artwork. Day and night use layered paper scenery. Care buttons, food choices and fish-match cards display the new sprites. Regular roaring looks upward without atomic breath; sleep has closed eyes.

City towers show progressive tears and break into rotating, falling facade pieces before the next building arrives. Small tanks use wooden control sticks, helicopters hang from strings, and jets are paper cutouts. Battle attacks retain their individual movements, with a paper atomic-breath ribbon and paper-edged enemy beams. The shell, 720-square controls, saved-pet format, sound recordings and growth rules are retained.

Validation: care, save migration/reset, battle, city, growth and input geometry regressions; production-renderer stills and animated previews; signed APK verification. Native dialogs, touch feel, sound and performance still need an Android device check. Install over your existing app to keep your pet. Artwork prompts and atlas notes: art/PAPER_ARTWORK.md.

## New in 2.2: distinct feeding fish
Salmon is a long coral-pink fish, tuna is blue and silver with yellow fins, and sardines are three small silver fish. The selected food now drives the feeding sprite and caption. Each uses its own source crop and natural aspect ratio through the approach, bite and shrink animation. Food, XP and favorite-food rewards are unchanged. Desktop previews cover all three foods at approach, bite and finish; device testing remains outstanding.

## New in 2.1: start a new pet
CARE > START NEW PET opens a confirmation naming the current pet. Confirming permanently replaces it with a fresh egg and resets XP, care history, cooldowns and encounters. Cancel, Back or dismiss keeps the current pet. Reminder opt-in and quiet hours are retained. The replacement and cleared encounter flags are saved together; the egg remains unhatched after reopening. Installing this update does not reset anything by itself.

## New in 2.0: daily pet care
- New installs begin with a glowing egg. Tap it five times to crack and hatch it. Installed pets remain hatched and keep XP, care stats, and ongoing encounters.
- Dedicated hungry, bored, sick and celebrating poses; a live attention message explains the current need. Tap Godzilla in the habitat, or use CARE > PET HIS HEAD, to bond. Head pats award joy and 1 XP at most once per minute.
- Radioactive messes accumulate about every three hours, capped at three. They accelerate cleanliness decline. CLEAN clears them and bathes the pet.
- Four hours of very low food or cleanliness can cause a recoverable tummy ache. CARE > MEDICINE cures it; food and hygiene still need attention. Sick pets rest instead of entering new battles. No death.
- SLEEP and CARE > TURN LIGHTS OFF create a nighttime routine. Sleeping with lights off restores 14 energy/hour; lights on restores 8. Wake turns the light back on. Nighttime prompts bedtime, but never forces it.
- FEED opens Salmon, Tuna and Sardines choices. Each pet has a favorite, which adds joy. The animated fish approach/toss remains. Meals increase fictional kaiju weight; bonding games reduce it slightly.
- CARE > PROFILE & NAME includes a rename field, age, weight, favorite food, personality, growth and care history. Existing pets start age/history tracking with this upgrade; their original birthday is unknown, so the profile labels the tracking start instead.
- PLAY > FISH MATCH is a six-card, three-pair memory game. Tap two cards; after a miss, the next tap starts a fresh attempt. Completion awards 5 XP and 15 joy, at most once per ten minutes. It can be replayed without rewards during cooldown.
- Every six comfortable hours earns 1 care XP, alongside the existing slow growth thresholds. Care affects personality: bonding creates Playful, restful sleep Dreamer, sustained good care Gentle, otherwise Curious. At Adult, these produce a visible heart, moon, guardian crown or explorer diamond crest. These are small cosmetic crests, not replacement monster species.
- CARE > ATTENTION REMINDERS is opt-in, off by default. Hourly inexact local jobs only notify for unmet needs, at most once per four hours, never while sleeping or foregrounded. Default quiet hours 21:00–08:00 can be changed in whole hours; equal start/end disables quiet hours. The Android settings button handles system-level notification control. No cloud or network service.
- GUIDE is now CARE; the guide remains inside the menu. The approved main button layout, city exit button, shell and palette remain.

The app now targets Android 13 (API 33), still with Android 8 minimum. It declares POST_NOTIFICATIONS for explicit opt-in permission, and RECEIVE_BOOT_COMPLETED so opted-in JobScheduler reminders survive reboot. Android may delay periodic work, and notification delivery requires system permission. See [Android notification permission](https://developer.android.com/develop/ui/views/notifications/notification-permission) and [periodic job scheduling](https://developer.android.com/reference/android/app/job/JobInfo.Builder#setPeriodic(long)).

### Version 2.0 verification
`bash check.sh` runs the care/battle/city regressions plus hatching, messes, illness, medicine, light-dependent recovery, bonding/game cooldowns, favorite foods, care XP, appearance branches, matching completion, quiet hours and attention eligibility. A SharedPreferences interface test verifies new-install eggs, partial egg progress, old-pet migration and care-field save/load. Production renderer previews cover seven care states at 720 × 720. Native dialogs, notification delivery and touch behavior have not been tested on an Android device/emulator.

## New in 1.9
A visible LEAVE CITY button at the top right returns home immediately, including during attack animations. The entry energy remains spent and unfinished cities do not receive completion rewards. The four attack buttons retain their existing size and position.

## New in 1.8
- Regular Roar uses a dedicated upward-facing, open-mouth sprite with subtle body animation and the existing Godzilla roar recording. It does not fire a beam. Atomic breath remains an attack in battle and City Stomp.
- Play opens two large native buttons: City Stomp and Monster Battle. Each costs 12 energy to enter; Godzilla must be awake.
- City Stomp replaces the old timed ten-building tap game. Break eight tall towers in sequence. Tower health rises from 150 to 290. All towers take multiple hits even at maximum level.
- Punch, Kick, Tail and Breath animate wind-up, contact and recovery. Damage lands at contact; further attacks wait for recovery. Towers show broken windows, cracks, debris and a collapse before the next tower appears. Tapping the current tower also punches.
- Moving tanks, helicopters and jets periodically enter. Tap their moving positions to stop them and earn 5 charge. Each surviving threat fires every 2.2 seconds for 3/4/5 city health damage. The encounter has its own 100 health; retreat does not harm the pet. Hit areas include a small touch margin. Threats remain tappable while Godzilla attacks.
- Clear all eight buildings for 20 XP and up to 25 joy. Rewards are granted once. Back offers to leave the city; entry energy stays spent.
- City time, attack phases, health, towers and threats save locally. Backgrounding pauses all city timers, with no offline military attacks. Monster battles retain their existing pause/save behavior.

## Growth and damage
Five levels: Hatchling (0 XP), Junior (250), Adult (750), Titan (1800), King of Monsters (3600). Existing XP is not erased or rescaled. Each level visibly increases sprite size in the habitat, city and battles. Each level adds 20% of hatchling base damage, up to 1.8× at level 5, rounded to the nearest integer.

Hatchling base attacks: Punch 14 (+18 charge), Kick 18 (+12 charge), Tail 21 (+8 charge), Breath 34 (costs 45 charge). Start city/battle at 60 charge, cap 100. Monster weaknesses add another 6 damage; displayed battle damage includes this bonus. Battle level is captured on entry and saved with the encounter.

## Care and battles
Feed, wash, play and sleep to care for Godzilla. Sleep has a closed-eye pose and restores 14 energy/hour. Care decline while away is capped at 24 hours. Your pet never dies. Regular Roar costs 15 energy. Day is 06:00–17:59 by device clock; night is 18:00–05:59. No location lookup.

Play → Monster Battle → choose Mechagodzilla, King Kong, King Ghidorah, Rodan, Gigan or Anguirus. Attack controls replace the care buttons. Opponents counterattack after recovery, with signature attacks every third turn. Wins give 25 XP and up to 20 joy; losses reduce joy by 5. Back offers to leave. Each monster retains its distinct animations.

The approved shell and colors are preserved. Controls fit outside the shell; other screen shapes scale uniformly with letterboxing. Swipe from an edge to reveal Android navigation. Sounds use media volume. Uninstalling or clearing app data erases progress. No accounts, ads or network permissions. Reminders use optional local notifications.

## Verification
Built against Android API 31, dexed for min API 26, aligned, and APK v2/v3 signatures verified. The signing certificate matches 1.7. Pure Java tests cover care, growth boundaries, battle timing and damage, charge, six opponents, unique rewards, city multi-hit rules, sequential tower completion at every level, all three military types, movement/hit testing, safe retreat, and background pause. Legacy geometry/day-night tests also remain.

The production renderer was inspected through Java2D at 720 × 720 for the upward roar, five growth sizes, city attacks and moving threats, and scaled monster combat. A city animation GIF is included beside the APK. These are desktop renderer previews, not Android screenshots. Native dialog appearance, touch behavior, audio and performance still need physical-device testing; no emulator or device was available.

## Build
Run `bash build.sh` with JDK 11, Android build-tools 34 and Android platform 31. Override `JDK`, `BT` and `ANDROID_JAR` as needed. Preserve `.signing/personal.jks` to sign compatible updates; the source archive excludes keys and build outputs. Avoid Java lambdas in this manual D8 configuration.

Portable checks: run `bash check.sh`; Android activity, UI and service classes are excluded from desktop execution. The SharedPreferences test uses the Android interface with an in-memory implementation. SkinPreview, UpdatePreview, RampagePreview and CarePreview use Java2D for desktop renders.

## Artwork and audio
See art/ARTWORK.md for asset sources and generation prompts. The upward roar sprite was created with the built-in image generator from the approved pet sprite. Military vehicles and tower damage are rendered in code. Prior sprites, fish and shell assets are retained.

Roar recording: https://wikizilla.org/wiki/File:Godzilla_gmk_roar.mp3 (Godzilla GMK, 5.02 seconds). Third-party fan-reference material, not an original or openly licensed recording. Stomp is an original synthesized impact/debris WAV from make_stomp.py.
