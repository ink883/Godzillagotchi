# Sound update — Godzillagotchi 3.2

The same sound set works across Classic, Puppet Theater, Vinyl Toy and LCD.

- Cleaning: a 1.8-second sequence of original synthesized water-bubble plips and light fizz starts with the bath.
- Feeding: a 0.65-second original synthesized crunchy/wet chewing sequence starts 1.5 seconds into the 2.2-second feeding animation, at the bite rather than the menu tap.
- Opponents: each has a separate classic source roar. It plays on entering a new fight and once when that opponent begins its counterattack wind-up. Beginning a player attack stops the prior opponent roar; Godzilla's existing recording is retained.
- Backgrounding, leaving a fight, sleeping or replacing the pet cancels relevant care cues. Backgrounding stops all new-effect streams and discards pending playback rather than resuming stale sounds. Delayed sample loads expire after 250 ms. Starting another action invalidates pending chewing.

## Monster source recordings

Downloaded from Wikizilla's public media host. These are existing character recordings, not newly synthesized impersonations. Source filenames and SHA-256 checksums are recorded in `audio-originals/sources.json`. The recordings remain associated with their original rights holders; no license grant is represented by these credits.

| App opponent | Source file page |
| --- | --- |
| Mechagodzilla | [MechaGodzilla Showa Roar](https://wikizilla.org/wiki/File:MechaGodzilla_Showa_Roar.ogg) |
| King Kong | [Toho King Kong Roar](https://wikizilla.org/wiki/File:Toho_King_Kong_Roar.ogg) |
| King Ghidorah | [King Ghidorah Roar Showa](https://wikizilla.org/wiki/File:King_Ghidorah_Roar_Showa.ogg) |
| Rodan | [Rodan SFX](https://wikizilla.org/wiki/File:Rodan_SFX.ogg) |
| Gigan | [Showa Gigan SFX](https://wikizilla.org/wiki/File:Showa_Gigan_SFX.ogg) |
| Anguirus | [Anguirus Roar Showa](https://wikizilla.org/wiki/File:Anguirus_Roar_Showa.ogg) |

## Processing and reproduction

`make_audio.py` creates bubble and munch effects using deterministic standard-library synthesis. With `--ffmpeg /path/to/ffmpeg`, it also decodes the six original Ogg files to 22,050 Hz mono 16-bit PCM WAV, removes leading/trailing low-level silence, normalizes conservatively and fades short edges. Pitch and playback rate of monster roars stay unchanged. `audio-originals/processed.json` records output duration, peak, RMS, trim boundaries and checksums. Runtime volume is 75% for bubbles, 90% for chewing and 72% for opponent roars; device media volume remains in control.

The converter used for this build was the temporary imageio-ffmpeg 0.6.0 bundled executable. The Android app requires no converter, network or new permission. All eight new clips are packaged in `res/raw`; each decoded sample is below SoundPool's one-megabyte limit.

## Validation

`SoundCuesTest` checks bite timing, one-shot behavior, superseded meals, background/reset cancellation, expiry, all six opponent mappings, counterattack transitions and no replay on defeat or resume. `tests/audio_check.py` verifies eight distinct outputs, PCM format, decoded sizes, non-silent levels, bounded peaks and click-free endpoints; it also assembles an offline preview in this order: bubbles, munch, Mechagodzilla, Kong, Ghidorah, Rodan, Gigan, Anguirus. Existing gameplay/theme/save tests are retained. Android device audio quality, output routing and native lifecycle behavior still need device validation.

## Version 3.3 Foley
- Eating: qubodup, [Eating With Open Mouth](https://freesound.org/people/qubodup/sounds/210426/), CC0; public HQ MP3 preview, excerpt at 2.0 seconds, normalized with faded edges.
- [Wilhelm Scream](https://commons.wikimedia.org/wiki/File:Wilhelm_Scream.ogg), CC0 as listed by Wikimedia Commons; normalized with faded edges. Plays on 5% of successful military vehicle destructions.
- Opponent roars play only at special attack wind-up, never on entry or basic attacks.
