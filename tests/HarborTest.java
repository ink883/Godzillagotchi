import com.nova.godzillagotchi.*;

public class HarborTest {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void main(String[] args) throws Exception {
        Pet pet = new Pet(0);
        Harbor harbor = new Harbor(100, 0);
        check(!harbor.openCrate(100, pet), "crate not visible at departure");
        check(harbor.openCrate(3000, pet), "crate opens during the walk");
        check(!harbor.openCrate(3100, pet) && pet.harborFish(0) == 1, "crate rewards once");
        check(!harbor.catchFish(4000, pet), "no fishing during travel");
        check(harbor.catchFish(7464, pet) && pet.harborFish(0) == 2, "in-zone catch awards salmon");
        check(!harbor.catchFish(7465, pet), "debounce between casts");
        check(harbor.catchFish(8314, pet) && pet.harborFish(1) == 0, "miss grants no fish");
        check(harbor.catchFish(9596, pet) && pet.harborFish(2) == 1, "last cast awards sardines");
        check(harbor.finished() && !harbor.catchFish(12000, pet), "three casts only");
        pet.food = 10;
        pet.feed(0);
        check(pet.food == 40 && pet.harborFish(0) == 1, "fresh fish is used and fills more");
        pet.feed(1);
        check(pet.food == 62 && pet.harborFish(1) == 0, "regular feed remains available");

        SkinPreview preview = new SkinPreview(args[0]);
        SkinRenderer.State state = new SkinRenderer.State();
        state.pet = pet;
        state.harbor = new Harbor(0, 1);
        for (int theme = 0; theme < 4; theme++) {
            state.theme = theme;
            state.harborNow = 3000;
            preview.frame(state, 720);
            state.harborNow = 7364;
            preview.frame(state, 720);
        }
        state.theme = Theme.PUPPET;
        preview.render(state, args[1]);
        System.out.println("PASS: harbor discovery, catch timing, fresh feeding, and all four renderers");
    }
}
