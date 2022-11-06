package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 20);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenVerticesGreaterThan5() {
        Box box = new Box(8, 30);
        int num = box.getNumberOfVertices();
        assertThat(num).isGreaterThan(5);
    }

    @Test
    void whenEdgeIsZero() {
        Box box = new Box(2, 0);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(-1);
    }

    @Test
    void whenExist() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void whenNotExist() {
        Box box = new Box(1, 1);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenGetAreaForCube() {
        Box box = new Box(8, 8);
        double area = box.getArea();
        assertThat(area).isPositive().isEqualTo(384.0d, withPrecision(0.001d));
    }

    @Test
    void whenGetAreaForTetrahedron() {
        Box box = new Box(4, 4);
        double area = box.getArea();
        assertThat(area).isPositive().isEqualTo(27.71d, withPrecision(0.01d));
    }
}