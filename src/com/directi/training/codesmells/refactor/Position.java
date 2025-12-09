package com.directi.training.codesmells.refactor;

public class Position
{
    private final int _row;
    private final int _column;

    public Position(int row, int column)
    {
        _row = row;
        _column = column;
    }

    public int getRow()
    {
        return _row;
    }

    public int getColumn()
    {
        return _column;
    }

    @Override
    public String toString()
    {
        return "(ROW: " + _row + ", COLUMN: " + _column + ")";
    }

    public Position translatedPosition(Direction direction)
    {
        return new Position(getRow() + direction.getRowOffset(), getColumn() + direction.getColumnOffset());
    }
    	
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Position))
            return false;
        Position otherPosition = (Position) obj;
        return this == obj || (_row == otherPosition.getRow() && _column == otherPosition.getColumn());
    }

    //Logika pergerakan pindahin ke sini
    public boolean isStraightPathTo(Position target)
    {
        return Math.abs(this._row - target._row) == Math.abs(this._column - target._column)
                || this._row == target._row
                || this._column == target._column;
    }

    public Direction directionTo(Position target)
    {
        int rowDir = Integer.compare(target._row, this._row);
        int colDir = Integer.compare(target._column, this._column);
        return new Direction(rowDir, colDir);
    }
}