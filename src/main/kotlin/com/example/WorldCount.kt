package com.example

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.ExecutionEnvironment
import org.apache.flink.api.java.operators.DataSource
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.util.Collector

fun main(args: Array<String>) {

    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment()
    var dataSource: DataSource<String> = env.readTextFile(args.get(0))

    dataSource.flatMap(
        FlatMapFunction { value, out: Collector<Tuple2<String, Int>> ->
            value.split(" ")
                .iterator()
                .forEach { out.collect(Tuple2(it, 1)) }
        })
        .groupBy(0)
        .sum(1)
        .print()

    print("Word Count !!")
}