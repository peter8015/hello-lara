


def dp(n):
    if n < 2:
        return 1

    return dp(n - 1) + dp(n - 2)