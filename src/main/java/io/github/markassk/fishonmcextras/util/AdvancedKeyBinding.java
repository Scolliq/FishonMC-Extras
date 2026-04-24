package io.github.markassk.fishonmcextras.util;

import net.minecraft.client.option.KeyBinding;

public class AdvancedKeyBinding extends KeyBinding {
    public AdvancedKeyBinding(String translationKey, int code, String category) {
        // 1.21.11: Category is now a record/class, not a translation-key string.
        // MISC groups our binds under "Miscellaneous" in the controls screen.
        super(translationKey, code, KeyBinding.Category.MISC);
    }

    public void onPressed(Runnable runTrue) {
        while (this.wasPressed()) {
            runTrue.run();
        }
    }
}
