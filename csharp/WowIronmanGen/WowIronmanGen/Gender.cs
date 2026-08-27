namespace WowIronmanGen;

public enum Gender
{
    Female,
    Male
}

public static class GenderExtensions
{
    public static string ToDisplayString(this Gender gender)
    {
        return gender.ToString().ToLower();
    }
}
