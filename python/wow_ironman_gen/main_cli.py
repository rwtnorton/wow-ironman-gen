#!/usr/bin/env python3
from wow_ironman_gen.loader import Loader
from wow_ironman_gen.toon import Toon
import argparse


def main():
    parser = argparse.ArgumentParser(
        prog='wow_ironman_gen',
        description='Generate a random Toon suitable for WoW Ironman')
    parser.add_argument('--json_file',
                        type=str,
                        required=False)
    args = parser.parse_args()
    if args.json_file is not None:
        loader = Loader(path=args.json_file)
    else:
        loader = Loader(path="wow_ironman_gen/data/pandaria.json")
    t = Toon.random_toon(loader.data)
    print(t)


if __name__ == '__main__':
    main()
