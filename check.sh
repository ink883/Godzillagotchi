#!/bin/bash
set -euo pipefail
cd "$(dirname "$0")"
JDK="${JDK:-/Applications/Kindle Previewer 3.app/Contents/lib/fc/jre}"
ANDROID_JAR="${ANDROID_JAR:-/private/tmp/rg-rotate-android-platform/android-12/android.jar}"
mkdir -p build/tests
files=()
for f in src/com/nova/godzillagotchi/*.java; do
 case "$f" in */MainActivity.java|*/CareUI.java|*/Attention.java|*/GameSounds.java) ;; *) files+=("$f");; esac
done
"$JDK/bin/javac" -cp "$ANDROID_JAR" -d build/tests "${files[@]}" tests/*.java
for test in PetTest CityTest SkinTest BattleTest RampageTest CareTest CareStoreTest ThemeTest SoundCuesTest InteractionTest; do
 "$JDK/bin/java" -Djava.awt.headless=true -cp "build/tests:$ANDROID_JAR" "$test"
done
"$JDK/bin/java" -Djava.awt.headless=true -cp build/tests CarePreview res/drawable-nodpi ../godzillagotchi-3.3

python3 tests/audio_check.py
