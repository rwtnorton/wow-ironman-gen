#!/usr/bin/env -S dotnet fsi
#load "Domain.fs"
open Domain

let rand = System.Random()
let theToon = Toon.Generate rand
printfn $"{theToon}"
