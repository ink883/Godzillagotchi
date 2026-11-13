# Approved shell skin, version 1.2

Tool: built-in image_gen. No CLI fallback used.

Assets are saved in res/drawable-nodpi:
- shell.png: unchanged approved square shell-and-buttons concept from this chat. The live renderer covers the sample scene, header, and care values with current game content. Button art is retained directly.
- pet_sprite.png: transparent Godzilla sprite derived from that approved concept.
- city_atlas.png: square day/night texture atlas; upper half night, lower half day. A small horizontal crop preserves the scene aspect ratio.

## Final sprite prompt
Extract ONLY the cute pixel-art Godzilla character from the supplied app mockup as a standalone sprite on a genuinely transparent background. Preserve the exact character design: green body, rounded cute head facing right, large black and cream eye, open smiling mouth with pink tongue, cream belly, bright cyan dorsal spines and curved tail extending left, small outstretched arms, chunky feet. Keep pixel-art edges and palette and full body including tail, no shell, no skyline, no UI, no lettering, no floor, no shadow. Center the character with a narrow transparent margin and no cropping. This sprite will replace the character in the same app, so match the approved image closely.

## Final atlas prompt
Edit this two-panel skyline texture atlas into a SQUARE 1:1 output image, ideally 1024 x 1024. The output MUST be square, not wide. Top panel fills exactly the upper half of the square; bottom panel fills exactly the lower half of the square, no gap. Each individual panel has 2:1 landscape proportions, so a 1024x1024 canvas has two 1024x512 panels. Recompose the pixel art rather than stretching it: ROUND moon and ROUND sun, normal building proportions. Keep orange Tokyo tower toward left, blue Tokyo skyline on right, moon at upper right of upper panel, sun at upper right of lower panel, bay reflections and foreground buildings. Same city composition in both panels, upper night and lower day. No Godzilla, no labels, no borders. Match the supplied reference's pixel art, palette and detailed windows. Need square output to fit game textures without any squashed or elongated circular moon or sun.

## Visual verification
SkinPreview.java invokes the same SkinRenderer.java used by the Android application, with Java2D standing in for Android Canvas. Five 720-square previews are saved beside the APK. Device font rendering and native controls are not emulated. No Android emulator/device test was performed.


# Version 1.3 battle sprites

Tool: built-in image_gen, referenced approved pet_sprite.png. Final asset: res/drawable-nodpi/opponents.png. Transparent 3-column, 2-row atlas. Source rectangles are measured from alpha bounds and used without modifying the PNG. Top: Mechagodzilla, King Kong, King Ghidorah. Bottom: Rodan, Gigan, Anguirus.

## Initial opponent prompt
Create a transparent pixel-art sprite atlas for a cute Godzilla virtual pet fighting game. Reference image is the style guide only: match its chunky detailed pixels, dark outlines, rounded chibi monster proportions, cream highlights and saturated accents. Output ONE square image with exactly SIX separate full-body opponent sprites arranged in precisely 3 equal columns and 2 equal rows. All creatures face LEFT toward the player's Godzilla. Every creature entirely contained inside its own equal cell with at least 10 percent transparent padding around every edge; absolutely no overlap across cells. Transparent background, no shadows, no floor, no words, no UI. Top row left: MECHAGODZILLA, silver robotic Godzilla with segmented metal armor, dorsal plates and red eye. Top row middle: KING KONG, brown muscular gorilla with dark face, powerful fists, no weapons. Top row right: KING GHIDORAH, golden dragon with exactly three distinct horned heads on long necks, two large bat wings, two legs and twin tails. Bottom row left: RODAN, rust-red pterosaur kaiju with pointed beak, two horns, large wings and clawed feet. Bottom row middle: GIGAN, green-and-gold cyborg kaiju with red visor, hooked metal hands, silver buzzsaw belly and three dorsal fins. Bottom row right: ANGUIRUS, gray-brown quadrupedal ankylosaur kaiju with horned head, large cream spikes on armored back and long spiked tail. Make all six distinct and recognizable yet cute, in the same crisp pixel art style as reference. Each sprite should occupy about 75-80 percent of its cell, correctly facing left; the whole image is a square atlas, 3 columns by 2 rows.

## Final atlas repacking prompt
Repack these exact six character designs into a usable sprite atlas. CRITICAL: give EVERY sprite enormous clear transparent padding so no character touches or crosses its cell boundary. Keep output square, with exactly 3 equal columns and 2 equal rows, on transparent background. Each sprite must occupy only 60 percent of the width and 65 percent of the height of its cell and be centered in its cell. Thus there must be large TRANSPARENT GAPS between all six characters and all outer edges. Top row Mechagodzilla, King Kong, three-headed King Ghidorah. Bottom row Rodan, Gigan, Anguirus. Keep full wings, tails, spikes and feet entirely inside each cell. Preserve designs, colors, pixel-art style and all left-facing poses. No text, no lines or visible grid, no shadows, no background. This edit ONLY reduces and repositions sprites for clean nonoverlapping 3-by-2 cells.


# Version 1.4 sleeping sprite
Tool: built-in image_gen. Reference: pet_sprite.png. Saved asset: res/drawable-nodpi/pet_sleep.png.

Prompt: Edit this same cute green pixel-art Godzilla into an unmistakably SLEEPING sprite for the same game. Preserve its exact character design and palette: green scales, cream belly, cyan dorsal spines, dark pixel outline. Change pose to a relaxed seated crouch facing right, head slightly lowered, both eyes fully CLOSED shown as simple curved dark eyelid lines with NO visible iris or white eyeball, mouth fully CLOSED with a peaceful smile, arms resting on belly, tail curled around feet. Full body including dorsal spines and tail, large centered sprite with about 10 percent transparent margin. Genuine transparent background. No background, floor, shadow, text, Z letters, UI, pillow or other props. Keep crisp chunky pixel-art style. This must visually read as asleep even without any text.


# Version 1.5 attack poses
Tool: built-in image_gen. Reference: pet_sprite.png. Final saved asset: res/drawable-nodpi/attack_poses.png. The renderer animates pose selection, movement, trails, beam charging, contact bursts and recoil using battle time.

Prompt: Create a 2 by 2 transparent sprite atlas showing FOUR different attack poses of this EXACT same cute pixel-art Godzilla. Preserve identity: same rounded head, large eye, olive-green scales, cream belly, cyan dorsal spikes, tail and dark pixel outlines. Every pose faces RIGHT and all full bodies fit entirely within their equal quarter of a SQUARE image with 15 percent transparent margins and large transparent gutters. No text, no scenery, no ground shadows, no beam or impact effects. Top-left PUNCH: lean forward and thrust the right-facing arm far forward at chest height with a large closed fist, other arm tucked back. Top-right KICK: lean back slightly, planted left foot, other leg lifted and fully extended toward the right with visible cream claws, arms balancing. Bottom-left TAIL WHIP: torso twisted three-quarter away, head looks right, powerful thick green tail with cyan spikes sweeping horizontally far out to the RIGHT as the striking limb, both feet planted. Bottom-right ATOMIC BREATH: both feet braced, head forward to the right, jaws wide open, cyan dorsal spikes brightly illuminated; NO beam because app will animate the beam. Four clearly distinct full-body action silhouettes, crisp detailed chunky pixels matching supplied reference. Transparent background everywhere except sprites. Do not put any sprite over a cell boundary. Each pose centered in its quarter with generous empty margins.


# Version 1.6 opponent action atlas
Built-in image_gen was used with opponents.png as the design reference. Final asset: res/drawable-nodpi/enemy_attacks.png. Alpha bounds are used as source rectangles without changing the asset. Runtime code animates motion, rotation, beams, trails and impacts.

Initial prompt: Make an ACTION POSE version of this same six-monster pixel-art atlas. Preserve EXACT character identities, colors, cute detailed pixel-art rendering and LEFT-facing orientation. Square transparent PNG, exactly THREE equal columns and TWO equal rows. Each monster centered within its own cell with HUGE transparent margins: occupy at most 70 percent cell width and height. No overlap or cropping. Top-left Mechagodzilla: braced leaning left, mechanical arm extended horizontally LEFT in a powerful robot punch, open jaw ready to fire, red eye. Top-middle King Kong: a powerful overhead two-fist slam pose, both fists raised then projecting left, legs crouched ready to leap. Top-right King Ghidorah: all THREE long necks extended toward LEFT, all three mouths wide open to fire, wings spread behind. Bottom-left Rodan: horizontal swooping flight LEFT with wings swept back and talons extended forward, wings entirely inside cell. Bottom-middle Gigan: a low lunging LEFTward slash with both curved metal hook arms extended forward and red visor, visible buzzsaw belly. Bottom-right Anguirus: curled into a compact spiked armored ball, hint of horned head and claws tucked in, ready to roll LEFT. No beams, particles, motion trails, letters, labels, ground or shadows; app will animate those. All six complete sprites separated by large TRANSPARENT gutters so each can be cropped independently. Match the reference closely.

Final packing prompt: Keep these exact SIX action-pose monster designs, unchanged. ONLY fix packing: shrink every character so each occupies about HALF the width of its equal cell in a precise 3-column by 2-row SQUARE atlas. Huge transparent gutters, at least 20 percent empty margin on every side of every cell. Entire bodies, wings, hooks and tails must lie well inside the equal cells. Top row Mechagodzilla, Kong, Ghidorah. Bottom row Rodan, Gigan, Anguirus. No creature can cross a column boundary at one-third or two-thirds image width, or the horizontal halfway line. Preserve same left-facing poses, detailed pixel style, colors, transparent background and square output. Do not change characters, add labels, add grid lines, add effects, or add objects. Just substantially reduce sprites and center them in their own equal cells so they can be cropped independently.


# Version 1.7 fish
Tool: built-in image_gen. Saved asset: res/drawable-nodpi/fish.png.

Prompt: A single crisp pixel-art whole fish item sprite for a cute Godzilla virtual pet game. Horizontal side profile with HEAD on the LEFT and forked TAIL on the RIGHT, plump silvery blue body, teal-blue dorsal fin, lighter cream-silver belly, clearly visible round dark eye near the left head, simple gill line, small lower fins and appealing chunky silhouette. A fish, NOT fish bones, not sushi, not cooked or plated. Dark navy pixel outline, restrained bright pixel highlights, readable when displayed at 80 pixels wide. Retro detailed game sprite style, no text, no letters, no frame, no scenery, no water or shadow, genuinely transparent background. Center full fish with generous transparent padding; only one fish.

## Version 1.8: upward regular roar
Tool: built-in image_gen (transparent background), edit reference res/drawable-nodpi/pet_sprite.png.
Saved asset: res/drawable-nodpi/pet_roar.png.
Generated original: /Users/nlmminim4/.codex/generated_images/01a1944c-2ae7-7e71-b887-4c02a5d25c78/exec-1d5f22db-87fe-47ae-a4ac-e7d3e423f84b.png

Prompt: Use case: precise-object-edit. Asset: transparent pixel art sprite for existing Android pet game. Edit the referenced green cute Godzilla sprite into a ROARING UPWARD pose. Keep the same character, pixel style, green body, cream belly, cyan dorsal spines, full body and feet, and tail extending left, facing right. Change head and neck: tilt snout upward 45 degrees, mouth wide open roaring into sky; arms drawn back and chest lifted. Feet stay grounded. No fire, no breath beam, no particles, no text. Single complete character centered with 8 percent transparent margin on all sides on a square canvas. Actual transparent alpha background.

The production renderer crops normalized .05,.08,.84,.84, anchors the feet and adds subtle body motion. No breath effect is drawn for the habitat roar. City towers, cracks, debris, tanks, helicopters, jets and projectiles are deterministic Canvas/Java2D drawings in RampageRenderer.java. City attacks reuse the approved attack_poses.png sprites and BattleAnimation timing.


## Version 2.0: care expression atlas
Tool: built-in image_gen, transparent edit using pet_sprite.png as the reference.
Saved asset: res/drawable-nodpi/care_poses.png.
Generated original: /Users/nlmminim4/.codex/generated_images/01a1944c-2ae7-7e71-b887-4c02a5d25c78/exec-5c4f9c99-7d48-4d9f-a764-74b67a562ab5.png

Prompt: Use case precise-object-edit. Create a game sprite atlas of this exact cute pixel Godzilla, same green scales, cream belly, cyan spines and right-facing orientation. Square transparent canvas divided into exact 2 by 2 equal cells, no drawn grid. Each cell contains one complete full-body character with tail and feet and generous 12 percent transparent cell margins; no overlap between cells. TOP LEFT: hungry, large hopeful eyes looking up right, hands on tummy, closed small mouth. TOP RIGHT: bored and sulking, drooping eyelids, downturned closed mouth, drooped arms. BOTTOM LEFT: mild tummy ache, eyes squeezed shut, hand holding belly, tiny forehead sweat drop. BOTTOM RIGHT: happy celebration, smiling eyes, arms raised, joyful open mouth. Preserve recognizable design and consistent scale across cells. No text, props, backgrounds or beams. Actual transparent background.

Egg wobble/cracks, radioactive messes, care crests, hearts and dimmed habitat lights are rendered in native code by CareRenderer. Existing artwork is retained.

## Version 2.2: three distinct fish
Tool: built-in image_gen, transparent reference edit from fish.png.
Saved asset: res/drawable-nodpi/fish_types.png. Original asset retained.
Original generated file: /Users/nlmminim4/.codex/generated_images/01a1944c-2ae7-7e71-b887-4c02a5d25c78/exec-5306b026-ec94-4dd5-ae54-8a733f9486f9.png

Prompt: Use case precise-object-edit. Make a square transparent pixel-art FOOD SPRITE ATLAS matching the reference's crisp outlined game sprite style. Exactly three equally tall horizontal rows. Each row holds a separate food with ample transparent margins, no overlap, no text or grid. All fish face LEFT, head left tail right. TOP THIRD: one long salmon, clearly pink/coral body with dark reddish back, pale belly, small spots, recognizable salmon silhouette. MIDDLE THIRD: one chunky oval blue tuna, dark navy back silver belly, prominent bright yellow fins and yellow finlets, strong crescent tail. BOTTOM THIRD: three small slender silver sardines in a compact slightly staggered group, pale silver bodies teal backs and tiny fork tails. Make silhouette and palette obviously different even when rendered only 100 pixels wide. Each food/group centered within its row, entirely between x=10% and90%, with at least 5% total-canvas vertical empty margin from row boundaries. Transparent alpha everywhere outside the fish. No plates, scenery, labels, shadows or glow.

Alpha bounds measured with Java ImageIO. The renderer preserves each crop's aspect ratio. Food selection is copied from CareUI to MainActivity's transient fedFish field and renderer State.fishType, independent of the pet's favorite food.
