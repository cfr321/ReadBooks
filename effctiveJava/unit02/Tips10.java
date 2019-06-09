package effctiveJava.unit02;

import java.awt.*;

/**
 * @Author: 摇井
 * @CreateDate: Created in 2019/6/9 16:27
 * @Description: 覆盖equals时请遵守通用条约
 */
public class Tips10 {
    /**
     * 覆盖equals需要遵循的条约
     * 1、自反性， 2、对称性  3、传递性  4、一致性  5、对任何非null得取equals(null)必须为false
     *
     * 我们通常不要去覆盖equals方法，万不得已要去覆盖，也必须对关键域进行比较
     * 并且确定满足上面的五个条约。
     */
}
//对于要满足对称性和传递性时，这种由继承扩展得时非常麻烦
class Point{
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x;
    int y;
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point){
            Point point=(Point)obj;
            if(point.x==this.x &&
                point.y==this.y)
                return true;
        }
        return false;
    }
}
//如果直接继承，又扩展了属性，那时无法做到遵守equals条约的

/**
 * 1、如果直接添加比较color，结果就就是 point.equals(colorPoint）为true，反过来就为false
 * 2、如果添加判断，是point就用父类的不是就全部比较。则会违背传递性。
 * 3、正确的做法是不去扩展父类，而是持有一个父类的域
 *
 */
class ColorPoint {
    Point point;
    Color color;

    public ColorPoint(Point point, Color color) {
        this.point = point;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) return true;
        if(!(obj instanceof ColorPoint))
            return false;
        ColorPoint colorPoint=(ColorPoint)obj;
        return colorPoint.color.equals(this.color)&&colorPoint.point.equals(this.point);
    }
}