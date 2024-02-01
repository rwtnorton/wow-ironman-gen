#!/usr/bin/env python3


# import os.path as path
# import sys
#
# # sys.path.append(os.path.join(os.path.pardir, os.path.dirname(os.path.abspath(__file__))))
# print(path.dirname(path.abspath(__file__)))
# print(path.dirname(__file__))
# print(path.abspath(path.join(path.dirname(__file__), path.pardir, path.pardir)))
#
# sys.path.append(path.abspath(path.join(path.dirname(__file__), path.pardir, path.pardir)))
# sys.path.append(path.abspath(path.join(path.dirname(__file__), path.pardir)))
# for p in sys.path:
#     print(f"- {p}")
#

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
