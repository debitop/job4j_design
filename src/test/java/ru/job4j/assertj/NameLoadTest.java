package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyArrayNames() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkNamesStartWithEqually() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=key", "value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain a key", names[0]));
    }

    @Test
    void checkNamesNotContainsEqually() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"key", "value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain the symbol \"=\"", names[0]));
    }

    @Test
    void checkNamesNotContainsValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"ke=y", "value="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value", names[1]);
    }
}