use std::fs::File;
use std::io;
use std::io::prelude::*;
use std::io::BufReader;

fn main() -> io::Result<()> {
    let f = File::open("../input.txt")?;
    let reader = BufReader::new(f);

    let lines = reader.lines().map(|l| l.unwrap());
    let mut count: u8 = 0;
    for line in lines {
        let mut passed = true;
        let mut i: u8 = 0;
        let mut prev_num: u8 = 0;
        let mut increasing = true;
        let mut decreasing = true;
        for chunk in line.split_whitespace() {
            let num: u8 = chunk.parse().unwrap();
            if i != 0 {
                let diff: i16 = (num as i16) - (prev_num as i16);
                increasing = increasing && diff > 0;
                decreasing = decreasing && diff < 0;
                passed = passed && diff.abs() < 4;
            }
            prev_num = num;
            i += 1;
        }

        if (increasing || decreasing) && passed {
            count += 1;
        }
    }
    println!("{count}");
    Ok(())
}
